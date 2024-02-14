package com.spotune.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotune.demo.entity.Playlist;
import com.spotune.demo.repository.PlaylistRepository;

import jakarta.transaction.Transactional;
@Service
public class PlaylistServiceImplementation implements PlaylistService {
	@Autowired
	PlaylistRepository repo;
	@Override
	public void addPlaylist(Playlist playList) {
		repo.save(playList);
	}
	@Override
	public List<Playlist> fetchAllPlaylist() {
		return repo.findAll();
	}
//	@Transactional
//	@Override
//	public void deleteSongBySongId(int song) {
//		repo.deleteBySongsId(song);
//		
//	}
}
