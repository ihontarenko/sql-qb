SELECT

`column_name`.id as uid,
user_id as uid,
count(users.id) as result,
group_concat(u.ID, count(ids), sum(count(test.id))) str,
users.`id` as u123,
u.name,
`users`.`email`

FROM `table_name` WHERE `column_name` = 'string\'s value';