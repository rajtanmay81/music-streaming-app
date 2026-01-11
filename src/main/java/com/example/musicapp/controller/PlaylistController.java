package com.example.musicapp.controller;

import com.example.musicapp.model.Playlist;
import com.example.musicapp.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    // ---------------- CREATE PLAYLIST ----------------
    @PostMapping
    public Playlist createPlaylist(@RequestParam String name) {
        return playlistService.createPlaylist(name);
    }

    // ---------------- GET ALL PLAYLISTS ----------------
    @GetMapping
    public List<Playlist> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    // ---------------- GET SINGLE PLAYLIST ----------------
    @GetMapping("/{id}")
    public Playlist getPlaylist(@PathVariable Long id) {
        return playlistService.getPlaylist(id);
    }

    // ---------------- DELETE PLAYLIST ----------------
    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
    }

    // ---------------- ADD SONG TO PLAYLIST ----------------
    @PostMapping("/{playlistId}/songs/{songId}")
    public Playlist addSongToPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long songId) {

        return playlistService.addSong(playlistId, songId);
    }

    // ---------------- REMOVE SONG FROM PLAYLIST ----------------
    @DeleteMapping("/{playlistId}/songs/{songId}")
    public Playlist removeSongFromPlaylist(
            @PathVariable Long playlistId,
            @PathVariable Long songId) {

        return playlistService.removeSong(playlistId, songId);
    }
}
