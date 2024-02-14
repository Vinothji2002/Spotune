package com.spotune.demo.service;

import java.util.List;

import com.spotune.demo.entity.Song;

public interface SongService {
	public void addSong(Song song);

	public List<Song> fetchAllSongs();

	public boolean songExist(String name);

	public void updateSong(Song s);

//	public void deleteSong(int id);

//	public void deletePlaylistBySongId(int song);

}
