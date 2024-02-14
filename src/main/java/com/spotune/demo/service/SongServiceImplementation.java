package com.spotune.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotune.demo.entity.Song;
import com.spotune.demo.repository.SongRepository;

import jakarta.transaction.Transactional;

@Service
public class SongServiceImplementation implements SongService {
	@Autowired
	SongRepository repo;
	@Override
	public void addSong(Song song) {
		repo.save(song);
	}
	@Override
	public List<Song> fetchAllSongs() {
		
		return repo.findAll();
	}
	@Override
	public boolean songExist(String name) {
		Song song=repo.findByName(name);
		if(song==null)
			return false;
		else
			return true;
	}
	@Override
	public void updateSong(Song s) {
		// TODO Auto-generated method stub
		repo.save(s);
	}
//	@Transactional
//	@Override
//	public void deleteSong(int id) {
//		repo.deleteById(id);
//	}
//	@Transactional
//	@Override
//	public void deletePlaylistBySongId(int song) {
//		repo.deleteBySongId(song);
//	}
}
