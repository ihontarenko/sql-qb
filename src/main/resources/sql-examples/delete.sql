delete G.* from STRAiGHT_join
users U
Inner join groups G On (G.id = U.gid)
where u.id > 1