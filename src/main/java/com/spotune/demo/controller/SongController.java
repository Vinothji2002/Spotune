package com.spotune.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spotune.demo.entity.Song;
import com.spotune.demo.service.PlaylistService;
import com.spotune.demo.service.SongService;

import jakarta.transaction.Transactional;

@Controller
public class SongController {
	@Autowired
	SongService service;
	@Autowired
	PlaylistService playlistService;
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		boolean songStatus=service.songExist(song.getName());
		if(songStatus==false) {
			service.addSong(song);
			System.out.println("Song added successfully");
		}
		else {
			System.out.println("Song already exist");
		}
		return "adminHome";
	}
	@GetMapping("/viewSongs")
	public String viewSongs(Model model) {
		
		List<Song> songList=service.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "displaySongs";
	}
	@GetMapping("/playSongs")
	public String playSongs(Model model) {
		boolean premiumUser=true;
		if(premiumUser==true) {
			List<Song> songList=service.fetchAllSongs();
			model.addAttribute("songs", songList);
			return "displaySongs";
		}
		else
			return "makePayment";
	}
	@GetMapping("/deleteSongs")
	public String delteSongs(Model model) {
		List<Song> songList = service.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "deleteSongs.html";
	}
//	@Transactional
//	@PostMapping("/deleteMarkedSongs")
//	public String deleteMarkedSongs(@RequestParam List<Integer> songList) {
//		for(int song:songList) {
//			service.deletePlaylistBySongId(song);
//			playlistService.deleteSongBySongId(song);
//			service.deleteSong(song);
//		}
//		return "adminHome";	
//	} 
}
