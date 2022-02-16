-- Table: public.BOOK_TAGS

-- DROP TABLE public."BOOK_TAGS";

CREATE TABLE public."BOOK_TAGS"
(
    "ISBN_13" "char"[] NOT NULL,
    "Tag_Name" "char"[] NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE public."BOOK_TAGS"
    OWNER to postgres;

INSERT INTO public."BOOK_TAGS"(
	"ISBN_13", "Tag_Name")
	VALUES (112233445, Fiction);