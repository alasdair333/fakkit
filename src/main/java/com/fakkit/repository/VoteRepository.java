package com.fakkit.repository;

import com.fakkit.model.Post;
import com.fakkit.model.User;
import com.fakkit.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    public Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

}
