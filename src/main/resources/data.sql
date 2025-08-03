-- Gallery 데이터 삽입 (10개)
INSERT INTO gallery (name, display_name, description, created_at, updated_at) VALUES
                                                                                  ('baseball', '국내야구 갤러리', '한국 프로야구에 대한 이야기를 나눕니다.', NOW(), NOW()),
                                                                                  ('soccer', '해외축구 갤러리', '유럽 5대 리그 및 이적 시장 소식', NOW(), NOW()),
                                                                                  ('game-lol', '리그 오브 레전드 갤러리', '롤 관련 정보, 꿀팁, e스포츠 소식', NOW(), NOW()),
                                                                                  ('stock', '주식 갤러리', '국내 및 해외 주식 투자 정보 공유', NOW(), NOW()),
                                                                                  ('movie', '영화 갤러리', '최신 영화 리뷰 및 추천', NOW(), NOW()),
                                                                                  ('drama', '드라마 갤러리', '방영 중인 드라마에 대한 이야기', NOW(), NOW()),
                                                                                  ('cat', '고양이 갤러리', '귀여운 고양이 사진과 정보를 공유합니다.', NOW(), NOW()),
                                                                                  ('dog', '강아지 갤러리', '사랑스러운 강아지들의 일상', NOW(), NOW()),
                                                                                  ('food', '음식 갤러리', '맛집 정보와 음식 사진', NOW(), NOW()),
                                                                                  ('travel', '여행 갤러리', '국내외 여행 후기 및 팁', NOW(), NOW());

-- Post 데이터 삽입 (갤러리별 1개씩, 총 10개)
INSERT INTO post (gallery_id, title, content, writer, password, created_at, updated_at) VALUES
                                                                                            (1, '오늘자 야구 경기 요약', '1회부터 홈런이 터지면서 경기가 폭발했네요!', '야구팬', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                            (2, '이번 시즌 챔스 우승팀 예측', '개인적으로는 맨시티가 유력해 보입니다.', '축구전문가', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                            (3, '페이커 신들린 플레이.gif', '방금 경기 보셨나요? 진짜 역대급이네요.', '롤창인생', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                            (4, '지금이라도 이 주식 타야 할까요?', '고민되네요. 너무 많이 오른 것 같은데...', '주린이', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                            (5, '최근 본 영화 중에 최고였습니다 (스포X)', '연출, 연기, 스토리 뭐 하나 빠지는 게 없네요. 꼭 보세요.', '씨네필', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                            (6, '이번 드라마 주인공 연기력 논란', '다들 어떻게 생각하시나요? 저는 좀 아쉬운데...', '드라마광', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                            (7, '저희 집 냥이 자랑합니다', '자는 모습이 너무 귀여워서 찍어봤어요.', '집사', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                            (8, '산책하다 만난 인절미', '너무 순하고 귀여워서 주인분 허락받고 찍었습니다.', '멍뭉이사랑', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                            (9, '오늘 저녁 메뉴 추천 받습니다', '결정장애 왔어요. 뭐 먹을까요?', '배고파', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                            (10, '1박 2일 국내 여행지 추천 좀', '혼자 조용히 다녀올 만한 곳 있을까요?', '여행가고싶다', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW());

-- Comment 데이터 삽입 (게시글 1번에 10개)
INSERT INTO comment (post_id, parent_id, content, writer, password, created_at, updated_at) VALUES
                                                                                                (1, NULL, '와, 오늘 경기 진짜 재밌었죠!', '댓글러1', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                                (1, NULL, '인정합니다. 직관 갔는데 소름돋았어요.', '댓글러2', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                                (1, 2, '부럽네요... 저는 집에서 봤는데도 소리 질렀습니다.', '대댓글러A', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                                (1, NULL, '상대팀은 오늘 너무 부진했던 듯', '댓글러3', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                                (1, NULL, '이 기세로 우승까지 가자!', '댓글러4', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                                (1, 5, '제발요!!', '대댓글러B', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                                (1, NULL, '내일 선발투수가 관건일 듯', '댓글러5', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                                (1, NULL, '이 글 보고 다시보러 갑니다 ㅋㅋ', '댓글러6', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                                (1, NULL, '작성자님 요약 감사합니다!', '댓글러7', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW()),
                                                                                                (1, NULL, '그래서 다음 경기는 언제죠?', '댓글러8', '$2a$10$t.V.2g3.dbzS2iWvH9.gA.L1g5g.E9.F/5jXJ5X.J5.e0kX.Z.5m', NOW(), NOW());