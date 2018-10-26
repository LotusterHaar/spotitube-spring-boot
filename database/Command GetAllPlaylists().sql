USE spotitube;
SELECT 
    playlists_view.*, token.token
FROM
    playlists_view
        LEFT JOIN
    token ON playlists_view.username = token.account_user
WHERE
    token.token = '1234-1234-1234-1234'

                        
