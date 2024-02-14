package com.spotune.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotune.demo.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer>{

	//public void deleteBySongsId(int song);

}
