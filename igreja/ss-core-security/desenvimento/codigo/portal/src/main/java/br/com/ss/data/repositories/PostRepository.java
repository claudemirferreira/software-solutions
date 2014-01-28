package br.com.ss.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ss.data.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
