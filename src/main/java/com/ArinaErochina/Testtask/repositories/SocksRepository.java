package com.ArinaErochina.Testtask.repositories;

import com.ArinaErochina.Testtask.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Sock,Integer> {
    Sock findSockByCottonPartAndColor (Long cottonPart, String color);
    List<Sock> findSockByColor(String color);
}
