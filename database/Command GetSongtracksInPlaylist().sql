SELECT 
    *
FROM
    spotitube.songtracks_view stv
        LEFT JOIN
    spotitube.playlist_has_tracks pht ON pht.track_id = stv.id
        AND pht.playlist_id = 1
WHERE
    stv.id IN (SELECT 
            track_id
        FROM
            spotitube.playlist_has_tracks
        WHERE
            playlist_id = 1)
