=================================================================================================================
LOGIN_DETAILS --->

INSERT INTO LOGIN_DETAILS(USER_NAME, PASSWORD, DISPLAY_NAME,USER_ROLE_TYPE,CREATED_BY,  LAST_UPDATED_BY) VALUES('srikanth', '5a15878df5a959e8e1f5c28bb6354a0b9722838d', 'Srikanth Ande','Admin','srikanth','srikanth');

UPDATE LOGIN_DETAILS
set display_name = 'Srikanth', last_update_date = current_timestamp
where user_id = 1;

=================================================================================================================
CLASS_AVBL_STANDARDS -->

INSERT INTO CLASS_AVBL_STANDARDS(STANDARD_NAME, DESCRIPTION, CREATED_BY,  LAST_UPDATED_BY) VALUES('1st Standard', 'This standard is relavant for 1st class students.','srikanth','srikanth');

=================================================================================================================
