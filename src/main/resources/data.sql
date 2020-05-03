insert into USERS (username, email, password, enabled) values (
    'admin',
    'admin@example.com',
    '$2a$10$UalEewK6Nf7CULBMUQClNe6GicCWtpQdpSvcWa6ZEX36Qrec5xhSe',
    'true'
);
insert into AUTHORITIES (id, name) values (
    1,
    'ROLE_ADMIN'
);
insert into USER_AUTHORITIES (user_id, authority_id) values (
    1,
    1
);
insert into RESULTS (id, name, description, way_to_get, above_norm, below_norm) values (
    1,
    'Ferrytyna',
    'Białko magazynujące żelazo wewnątrz komórki. Duże jej stężenie występuje w wątrobie. Niewielka ilość ferrytyny znajduję się we krwi. Występuje zależność między ilością żelaza a stężeniem ferrytyny w surowicy. Wynkik badania pozwala ocenić ilość zmagazynowego żelaza w organizmie, który z kolei bierze udział w syntezie hemoglobiny i tworzeniu się krwinek czerwonych',
    'Próbka krwi na czczo. Nie ma konieczności przerywać trwających terapii żelazem.',
    'Ferrytyna jest białkiem ostrej fazy jej nadmiar może wskazywać na stan zapalny w ogarnizmie lub zbyt duże spożycie alkoholu.',
    'Wczesny objaw wyczerpywania się stężenia żelaza w organizmie. Może być przyczyną m.in anemii, niedokrwistości.'
);
insert into RESULT_NORMS (id, description, result_id) values (
    1,
    'WIEK ROZRODCZY : 20-120 (dodać jednostkę) PO MENOPAUZIE : 30-280',
    1
);
insert into RESULT_NORMS (id, description, result_id) values (
    2,
    '30-280',
    1
);

insert into RESULTS (id, name, description, way_to_get, above_norm, below_norm) values (
    2,
    'Testosteron',
    'Jest najważniejszym męskim androgenem wydzielanym głównie przez jadra. U kobiet niewielkie jego ilości są syntesowane przez jajniki i w połowie przez korę nadnerczy. Testosteron krąży w osoczu. Z wiekiem stężenie testosteronu wolnego obnża się ',
    'Próbkę pobiera się z krwi o porannych godzinach na czczo. Naleźy niezwłocznie zamrozić.',
    'Występuje w przypadkach hirsutyzmu (nadmiernego owłosienia). Może świadczyć o przeroście nadnerczy. Objaw u kobiet to zatrzymanie miesiączki lub podejrzenie PCOS (zespołu policystycznych jajników) w takim przypadku należy wykonać USG. Testosteron może przekształcać się w DHT, który powowuje miniaturyzacje mieszka włosowego, a tym samym łysienie angrogenowe w okolicy czubka głowy i skroni.',
    'U mężczyzn może świadczyć o niewydolności jąder lub problemów na drodze podwzgórze-przysadka. Może mieć charakter nabyty, wiązany z zabiegiem chirurgiczny lub wskazywać na obecność guza.'
);
insert into RESULT_NORMS (id, description, result_id) values (
    3,
    'Tesosteron całkowity : 0,2-0,5 ng/ml (0,8-1,8 nmol/l)',
    2
);
insert into RESULT_NORMS (id, description, result_id) values (
    4,
    'Poniżej 50 r.ż. testosteron całkowity : 4-8 ng/ml (15-30 nmol/l)',
    2
);

