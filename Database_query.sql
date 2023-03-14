SELECT user.user_id, user.username, training.training_id,
training.training_date, count(user.user_id)
FROM User user, Training_details training 
where user.user_id = training.user_id
GROUP BY training.training_date , training.user_id, training.training_id
HAVING COUNT(user.user_id) >= 2
ORDER BY training.training_date DESC;




