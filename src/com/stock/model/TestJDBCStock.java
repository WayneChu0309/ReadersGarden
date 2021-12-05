package com.stock.model;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.google.gson.Gson;

public class TestJDBCStock {
	
	public static void main(String[] args) {
		StockVO vo = new StockVO();
//		vo.setAuthor();
		StockJDBCDAO dao = new StockJDBCDAO();
//		List<StockVO> stList = dao.getKeyword("美國隊長");
//		for (StockVO stock5 : stList) {
//			out.println("館藏編號 : " + stock5.getStockId());
//			out.println("館藏類別 : " + stock5.getTypeId());
//			out.println("館藏名稱 : " + stock5.getStockName());
//			out.println("館藏介紹 : " + stock5.getStockContent());
//			out.println("作者 : " + stock5.getAuthor());
//			out.println("出版社 : " + stock5.getPress());
//			out.println("出版日 : " + stock5.getIssuedDate());
//			out.println("評價分數 : " + stock5.getStockScore());
//			out.println("館藏圖片 : " + stock5.getStockImg());
//		}
		
		// 新增
//		StockVO stock1 = new StockVO();
//		stock1.setTypeId(1);
//		stock1.setStockName("哈利波特");
//		stock1.setAuthor("JK羅琳");
//		stock1.setPress("龍騰出版社");
//		cal.set(2010, cal.AUGUST, 3);
//		stock1.setIssuedDate(new Date(cal.getTimeInMillis()));
//		stock1.setStockContent("閃電少年的故事");
//		stock1.setStockScore(4.33);
//		byte[] pic;
//		try {
//			pic = getPictureByteArray("src/Stock/5分鐘後的意外結局：白色恐怖.png");
//			stock1.setStockImg(pic);
//			dao.add(stock1);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 修改
//		StockVO stock2 = new StockVO();
//		stock2.setTypeId(1);
//		stock2.setStockName("哈利波特");
//		stock2.setAuthor("JK羅琳");
//		stock2.setPress("龍騰出版社");
//		cal.set(2010, cal.AUGUST, 3);
//		stock2.setIssuedDate(new Date(cal.getTimeInMillis()));
//		stock2.setStockContent("閃電少年的故事");
//		stock2.setStockScore(4.33);
//		stock2.setStockId(1);
//		byte[] pic2;
//		try {
//			pic2 = getPictureByteArray("src/Stock/5分鐘後的意外結局：紅色惡夢.png");
//			stock2.setStockImg(pic2);
//			dao.update(stock2);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 刪除
//		dao.delete(10);
		
		// 單筆查詢
//		StockVO stock3 = dao.findByStockId(2);
//		out.println("館藏編號 : " + stock3.getStockId());
//		out.println("館藏類別 : " + stock3.getTypeId());
//		out.println("館藏名稱 : " + stock3.getStockName());
//		out.println("館藏介紹 : " + stock3.getStockContent());
//		out.println("作者 : " + stock3.getAuthor());
//		out.println("出版社 : " + stock3.getPress());
//		out.println("出版日 : " + stock3.getIssuedDate());
//		out.println("評價分數 : " + stock3.getStockScore());
//		out.println("館藏圖片 : " + stock3.getStockImg());
		
		// 全部查詢
//		List<StockVO> stList = dao.getAll();
//		for (StockVO stock4 : stList) {
//			out.println("館藏編號 : " + stock4.getStockId());
//			out.println("館藏類別 : " + stock4.getTypeId());
//			out.println("館藏名稱 : " + stock4.getStockName());
//			out.println("館藏介紹 : " + stock4.getStockContent());
//			out.println("作者 : " + stock4.getAuthor());
//			out.println("出版社 : " + stock4.getPress());
//			out.println("出版日 : " + stock4.getIssuedDate());
//			out.println("評價分數 : " + stock4.getStockScore());
//			out.println("館藏圖片 : " + stock4.getStockImg());
//		}
		
		// 頁面查詢
//		List<StockVO> stList = dao.getPage(2, 1);
//		for (StockVO stock5 : stList) {
//			out.println("館藏編號 : " + stock5.getStockId());
//			out.println("館藏類別 : " + stock5.getTypeId());
//			out.println("館藏名稱 : " + stock5.getStockName());
//			out.println("館藏介紹 : " + stock5.getStockContent());
//			out.println("作者 : " + stock5.getAuthor());
//			out.println("出版社 : " + stock5.getPress());
//			out.println("出版日 : " + stock5.getIssuedDate());
//			out.println("評價分數 : " + stock5.getStockScore());
//			out.println("館藏圖片 : " + stock5.getStockImg());
//		}
		
		
		
		// 新增爬蟲資料, 需先執行 StockType 產生類別 , 結束後執行 TestStockList 
		String bPath = "C:\\博客來資料\\書籍資料";
		String mPath = "C:\\博客來資料\\電影資料";
		File bf = new File(bPath);
		File mf = new File(mPath);
		String bfdirs[] = bf.list();  // 書籍類別資料夾名稱
		String mfdirs[] = mf.list();  // 電影類別資料夾名稱
		
		
		for (int i = 1; i < bfdirs.length + 1; i++) {
			File bfs = new File(bPath + "\\" + bfdirs[i-1]);
			String books[] = bfs.list();  // 全部書籍資料夾名稱
				for (int j = 0; j < books.length; j++) {
					String bookPath = bPath + "\\" + bfdirs[i-1] + "\\"+ books[j] + "\\" + books[j] + ".txt";
					String imgPath = bPath + "\\" + bfdirs[i-1] + "\\" + books[j] + "\\" + books[j] + ".png";
					try {
						out.println(bookPath);
						StockVO st = getBookInf(bookPath, new StockVO());
						if (st != null) {
							byte[] pic = getPictureByteArray(imgPath);
							st.setStockImg(pic);
							st.setTypeId(i);
							dao.add(st);							
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e){
						e.printStackTrace();
					}
				}
		}
		
		for (int i = 1; i < mfdirs.length + 1; i++) {
			File mfs = new File(mPath + "\\" + mfdirs[i-1]);
			String movies[] = mfs.list();  // 全部電影資料夾名稱
			for (int j = 0; j < movies.length; j++) {
				String moviePath = mPath + "\\" + mfdirs[i-1] + "\\"+ movies[j] + "\\" + movies[j] + ".txt";
				String imgPath = mPath + "\\" + mfdirs[i-1] + "\\" + movies[j] + "\\" + movies[j] + ".png";
				try {
					out.println(moviePath);
					StockVO st = getMovieInf(moviePath, new StockVO());
					if (st != null) {
						byte[] pic = getPictureByteArray(imgPath);
						st.setStockImg(pic);
						st.setTypeId(bfdirs.length + i);
						dao.add(st);
					} 
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		} 
		
		
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];  
		fis.read(buffer);  // 因為來源為硬碟, 不需使用buffer分段處理  // fis.read 回傳值為int, buffer的byte數量
		fis.close();
		return buffer;
	}
	
	public static StockVO getBookInf(String path, StockVO st) throws IOException {
		Calendar cal = Calendar.getInstance();
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder();
		String str;
		while ((str = br.readLine()) != null) {
			if (str.equals("作者簡介:"))
				break;
			sb.append(str);
		}
		int bookName = sb.indexOf("書名"); // start index 3, end index = author
		int author = sb.indexOf("作者");  // start index = autho + 3, end index = press
		int press = sb.indexOf("出版社"); // start index = press + 4, end index = issuedDate
		int issueDate = sb.indexOf("發行日"); // start index = issueDate + 4, end index = content
		int content = sb.indexOf("內容簡介"); // start index = content + 5
		String book = sb.substring(bookName+3, author);
		st.setStockName(book);
		String au = sb.substring(author+3, press);
		st.setAuthor(au);
		String pr = sb.substring(press+4, issueDate);
		st.setPress(pr);
		try {
			Integer year = Integer.parseInt(sb.substring(issueDate+4, content).split("/")[0]);
			Integer month = Integer.parseInt(sb.substring(issueDate+4, content).split("/")[1]) - 1;
			Integer day = Integer.parseInt(sb.substring(issueDate+4, content).split("/")[2]);
			cal.set(year, month, day);			
			st.setIssuedDate(new Date(cal.getTimeInMillis()));
		} catch (NumberFormatException e) {
			return null;
		}
		String cont = sb.substring(content+5);
		st.setStockContent(cont);
		return st;
	}
	
	public static StockVO getMovieInf (String path, StockVO st) throws IOException {
		Calendar cal = Calendar.getInstance();
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder();
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		int movieName = sb.indexOf("電影名稱"); // start index 5, end index = press
		int press = sb.indexOf("發行公司"); // start index = press + 5, end index = issuedDate
		int actor = sb.indexOf("演出者");
		int ticket = sb.indexOf("張數");
		int product = sb.indexOf("產品編號");
		int issueDate = sb.indexOf("發行日期"); // start index = issueDate + 5, end index = content
		int content = sb.indexOf("電影介紹"); // start index = content + 5
		String movie = sb.substring(movieName+5, press > actor ? press : actor);
		st.setStockName(movie);
		String pr;
		if (issueDate != -1) {
			pr = sb.substring(press+5, issueDate);
			try {
				Integer year = Integer.parseInt(sb.substring(issueDate+5, content).split("/")[0]);				
				Integer month = Integer.parseInt(sb.substring(issueDate+5, content).split("/")[1]) - 1;
				Integer day = Integer.parseInt(sb.substring(issueDate+5, content).split("/")[2]);	
				cal.set(year, month, day);
				st.setIssuedDate(new Date(cal.getTimeInMillis()));
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			pr = sb.substring(press+5, ticket > product ? ticket : product);
		}
		st.setPress(pr);
		String cont = sb.substring(content+5);
		st.setStockContent(cont);
		return st;
	}
	
	
}	
