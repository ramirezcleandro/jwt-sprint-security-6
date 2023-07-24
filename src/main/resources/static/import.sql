INSERT INTO public._user(
	enabled, id, email, first_name, last_name, password)
	VALUES (false, 1, "example@example.com", "First Name", "Last Name", ?);