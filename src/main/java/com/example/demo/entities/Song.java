package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.*;
@Entity
public class Song {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Song(int id, String name, String artist, String genre, String link, List<Playlist> playlists) {
		super();
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.genre = genre;
		this.link = link;
		this.playlists = playlists;
	}

	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
		int id;
		String name;
		String artist;
		String genre;
		String link;
		@ManyToMany
		List<Playlist> playlists;

		public Song() {
			super();
			// TODO Auto-generated constructor stub
		}
}
