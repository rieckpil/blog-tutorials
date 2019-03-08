CREATE OR REPLACE FUNCTION f_j_split_tag_to_string( text, text )
RETURNS SETOF text 
AS 'de.rieckpil.blog.Functions.splitTagString' LANGUAGE java;

SELECT f_j_split_tag_to_string( 'holidays - 2018 - sicily - tom > 1', '-' );
