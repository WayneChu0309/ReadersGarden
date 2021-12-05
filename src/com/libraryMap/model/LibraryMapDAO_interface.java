package com.libraryMap.model;

import java.util.List;

public interface LibraryMapDAO_interface {
	void add(LibraryMapVO libraryMap);
	void update(LibraryMapVO libraryMap);
	void delete(Integer mapId);
	LibraryMapVO findByMapId(Integer mapId);
	List<LibraryMapVO> getAll();
}
