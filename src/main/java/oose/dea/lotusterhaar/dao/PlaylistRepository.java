package oose.dea.lotusterhaar.dao;

import oose.dea.lotusterhaar.model.*;
import org.springframework.data.repository.CrudRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {

    void deleteBy(int id);


}

