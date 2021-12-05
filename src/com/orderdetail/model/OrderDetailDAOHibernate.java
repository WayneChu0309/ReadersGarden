package com.orderdetail.model;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.hibernate.HibernateUtil;


public class OrderDetailDAOHibernate implements OrderDetailDAO {
	
	private SessionFactory sessionFactory;
	
	public OrderDetailDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@Override
	public OrderDetailBean add(OrderDetailBean detailBean) {
		if (detailBean != null) {
			this.getSession().save(detailBean);
			return detailBean;
		}
		return null;
	}

	@Override
	public OrderDetailBean update(OrderDetailBean detailBean) {
		if (detailBean != null) {
			OrderDetailBean temp = this.getSession().get(OrderDetailBean.class, detailBean.getDetailId());
			if (temp != null) {
				return (OrderDetailBean) this.getSession().merge(detailBean);				
			}
		}
		return null;
	}

	@Override
	public OrderDetailBean findByPk(Integer detailId) {
		if (detailId != null) {
			OrderDetailBean temp = this.getSession().get(OrderDetailBean.class, detailId);
			if (temp != null) {
				return temp;
			}
		}
		return null;
	}

	@Override
	public List<OrderDetailBean> findByOrderId(Integer orderId) {
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OrderDetailBean> criteriaQuery = criteriaBuilder.createQuery(OrderDetailBean.class);
		Root<OrderDetailBean> root = criteriaQuery.from(OrderDetailBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("orderId"), orderId);
		criteriaQuery = criteriaQuery.where(p1);
		TypedQuery<OrderDetailBean> typedQuery = session.createQuery(criteriaQuery);
		List<OrderDetailBean> result = typedQuery.getResultList();
		return result;
	}

	@Override
	public List<OrderDetailBean> findByOverDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date nowDate = java.sql.Date.valueOf(df.format(new java.util.Date()));
		Session session = this.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<OrderDetailBean> criteriaQuery = criteriaBuilder.createQuery(OrderDetailBean.class);
		Root<OrderDetailBean> root = criteriaQuery.from(OrderDetailBean.class);
		Predicate p1 = criteriaBuilder.equal(root.get("detailStatus"), 1);
		Predicate p2 = criteriaBuilder.lessThan(root.get("stopdate"), nowDate);
		criteriaQuery = criteriaQuery.where(p1, p2);
		TypedQuery<OrderDetailBean> typedQuery = session.createQuery(criteriaQuery);
		List<OrderDetailBean> result = typedQuery.getResultList();
		return result;
	}
	
	@Override
	public byte[] getqrcode(String url) {
		int width = 300;
		int height = 300;
		String format = "jpg";
		// 設定編碼格式與容錯率
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 設置QRCode的存放目錄、檔名與圖片格式
		// 開始產生QRCode
		BitMatrix matrix;
		try {
			matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
			OutputStream os = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(matrix, format, os);
			return (((ByteArrayOutputStream)os).toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		OrderDetailDAO dao = new OrderDetailDAOHibernate(sessionFactory);
		
		OrderDetailBean detailBean = new OrderDetailBean();
		detailBean.setQrcode(dao.getqrcode("http://10.2.5.85:8081/TFA103_01/"));
		detailBean.setOrderId(1);
		detailBean.setVactId(1);
		detailBean.setStartdate(java.sql.Date.valueOf("2021-10-10"));
		detailBean.setStopdate(java.sql.Date.valueOf("2021-10-10"));
		detailBean = dao.add(detailBean);
		
		
		
		
		
		
		
		
		
		
		
		
		
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}

	

}
