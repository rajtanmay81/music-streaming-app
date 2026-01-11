package com.example.musicapp.service;

import com.example.musicapp.model.Playlist;
import com.example.musicapp.model.Song;
import com.example.musicapp.repository.PlaylistRepository;
import com.example.musicapp.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;

    public PlaylistService(PlaylistRepository playlistRepository,
                           SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    // CREATE PLAYLIST
    public Playlist createPlaylist(String name) {
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setSongs(new ArrayList<>());
        return playlistRepository.save(playlist);
    }

    // GET ALL
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    // GET ONE
    public Playlist getPlaylist(Long id) {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
    }

    // DELETE
    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    // ⭐ FINAL FIX — ADD SONG
    @Transactional
    public Playlist addSong(Long playlistId, Long songId) {

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        // ALWAYS INIT LIST
        if (playlist.getSongs() == null) {
            playlist.setSongs(new ArrayList<>());
        }

        // AVOID DUPLICATES
        boolean alreadyAdded = playlist.getSongs()
                .stream()
                .anyMatch(s -> s.getId().equals(songId));

        if (!alreadyAdded) {
            playlist.getSongs().add(song);
        }

        // SAVE OWNING SIDE
        return playlistRepository.save(playlist);
    }

    // REMOVE SONG
    @Transactional
    public Playlist removeSong(Long playlistId, Long songId) {

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        playlist.getSongs().removeIf(s -> s.getId().equals(songId));

        return playlistRepository.save(playlist);
    }
}
