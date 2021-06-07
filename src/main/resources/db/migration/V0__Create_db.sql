CREATE TABLE public.task (
    id bigint NOT NULL, 
   	created_at timestamp with time zone,
	updated_at timestamp with time zone,
	due_date timestamp with time zone,
	resolved_at timestamp with time zone,
	title character varying NOT NULL,
	description character varying NOT NULL,
	priority character varying NOT NULL,
	status character varying NOT NULL
    
);

CREATE SEQUENCE public.task_id_seq
    AS bigint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public.task_id_seq OWNED BY public.task.id;

ALTER TABLE ONLY public.task ALTER COLUMN id SET DEFAULT nextval('public.task_id_seq'::regclass);

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pk PRIMARY KEY (id);