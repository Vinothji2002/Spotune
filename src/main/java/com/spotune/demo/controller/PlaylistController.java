package com.spotune.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spotune.demo.entity.Playlist;
import com.spotune.demo.entity.Song;
import com.spotune.demo.service.PlaylistService;
import com.spotune.demo.service.SongService;

@Controller
public class PlaylistController {
	@Autowired
	SongService songService;
	@Autowired
	PlaylistService playlistService;
	
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		List<Song> songList=songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createPlaylist";
	}
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playList) {
		playlistService.addPlaylist(playList);
		
		List<Song> songList=playList.getSongs();
		for(Song s:songList) {
			s.getPlaylists().add(playList);
			songService.updateSong(s);
		}
		return "adminHome";
	}
	@GetMapping("/viewPlaylist")
	public String viewPlaylist(Model model) {
		List<Playlist> allPlaylists=playlistService.fetchAllPlaylist();
		model.addAttribute("allPlaylists", allPlaylists);
		List<Song> songList=songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "displayPlaylist";
	}
}
