package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Playlist {
	
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
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	public Playlist(int id, String name, List<Song> songs) {
		super();
		this.id = id;
		this.name = name;
		this.songs = songs;
	}
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
		int id;
		String name;
		@ManyToMany
		List<Song> songs;
		public Playlist() {
			super();
			// TODO Auto-generated constructor stub
		}
}
