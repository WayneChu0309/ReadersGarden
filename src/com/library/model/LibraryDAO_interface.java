package com.library.model;
import java.util.List;

public interface LibraryDAO_interface {
	void add(LibraryVO library);
	void update(LibraryVO library);
	void delete(Integer libId);
	LibraryVO findByLibraryId(Integer libId);
	List<LibraryVO> getAll();
}
