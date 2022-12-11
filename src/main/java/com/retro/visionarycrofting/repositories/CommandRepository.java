package com.retro.visionarycrofting.repositories;

import com.retro.visionarycrofting.entities.Client;
import com.retro.visionarycrofting.entities.Command;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommandRepository extends JpaRepository<Command, Long> {

    Command findByRef(String commandRef);

    List<Command> findByClient(Client client);
}
