package com.spotune.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotune.demo.entity.Song;

public interface SongRepository extends JpaRepository<Song, Integer> {

	Song findByName(String name);

	//void deleteBySongId(int song);

}
