package com.spotune.demo.service;

import java.util.List;

import com.spotune.demo.entity.Playlist;

public interface PlaylistService {
	public void addPlaylist(Playlist playList);

	public List<Playlist> fetchAllPlaylist();

	//public void deleteSongBySongId(int song);
}
