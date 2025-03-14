package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.SongService;

@Controller
public class PlaylistController {

	@Autowired
	SongService songService;
	private Object playlistService;
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		
		List<Song> songList = songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createPlaylist";
		
	}
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		
		
		 ((PlaylistController) playlistService).addPlaylist(playlist);
		
		List<Song> songList = playlist.getSongs();
		for(Song s : songList) {
			s.getPlaylists().add(playlist);
			songService.updateSong(s);
		}
		return "adminHome";
	}
	@GetMapping("/viewPlaylists")
	public String viewPlaylists(Model model) {
		
		List<Playlist> allPlaylists=   ((PlaylistController) playlistService).fetchAllPlaylists();
		model.addAttribute("allPlaylists", allPlaylists);
		
		return "displayPlaylists";
		
	}
	private List<Playlist> fetchAllPlaylists() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
