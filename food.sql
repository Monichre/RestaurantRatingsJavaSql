--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: foodtype; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE foodtype (
    id integer NOT NULL,
    type character varying
);


ALTER TABLE foodtype OWNER TO "Guest";

--
-- Name: foodtype_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE foodtype_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE foodtype_id_seq OWNER TO "Guest";

--
-- Name: foodtype_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE foodtype_id_seq OWNED BY foodtype.id;


--
-- Name: restaurant; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE restaurant (
    id integer NOT NULL,
    name character varying,
    review character varying,
    foodtypeid integer,
    rating integer
);


ALTER TABLE restaurant OWNER TO "Guest";

--
-- Name: restaurant_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE restaurant_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE restaurant_id_seq OWNER TO "Guest";

--
-- Name: restaurant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE restaurant_id_seq OWNED BY restaurant.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY foodtype ALTER COLUMN id SET DEFAULT nextval('foodtype_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY restaurant ALTER COLUMN id SET DEFAULT nextval('restaurant_id_seq'::regclass);


--
-- Data for Name: foodtype; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY foodtype (id, type) FROM stdin;
\.


--
-- Name: foodtype_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('foodtype_id_seq', 1, false);


--
-- Data for Name: restaurant; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY restaurant (id, name, review, foodtypeid, rating) FROM stdin;
\.


--
-- Name: restaurant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('restaurant_id_seq', 1, false);


--
-- Name: foodtype_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY foodtype
    ADD CONSTRAINT foodtype_pkey PRIMARY KEY (id);


--
-- Name: restaurant_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY restaurant
    ADD CONSTRAINT restaurant_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

