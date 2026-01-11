package com.example.musicapp.service;

import com.example.musicapp.model.Song;
import com.example.musicapp.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    // CREATE
    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    // READ ALL
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    // READ ONE
    public Song getSong(Long id) {
        return songRepository.findById(id).orElseThrow();
    }

    // UPDATE
    public Song updateSong(Long id, Song updated) {
        Song song = getSong(id);
        song.setTitle(updated.getTitle());
        song.setArtist(updated.getArtist());
        song.setFilePath(updated.getFilePath());
        return songRepository.save(song);
    }

    // DELETE
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }
}
