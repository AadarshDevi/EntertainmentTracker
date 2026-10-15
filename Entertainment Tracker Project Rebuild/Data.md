> [!NOTE]
> Check `DataTree.drawio`
# Movie

1. Entertainment ID
2. Title
3. Runtime
4. Release Date: LocalDate
5. Production Companies: List
6. Status
7. Type
8. Tags: List
9. Collection: HashMap
10. isSpecial
11. isFavorite
12. isPilot

# Show

1. Entertainment ID
2. Title
3. Release Date
4. Episode Count
5. Production Companies
6. Status
7. Type
8. Tags
9. Episode List
10. Collection
11. isSpecial
12. isPilot
13. isFavorite

# Comic

1. Entertainment ID
2. Title
3. Release Date
4. Issue Count
5. Status
6. Type
7. Issue List
8. Collection
9. Tags
10. isFavorite 
# Book

1. Entertainment ID
2. Title
3. Release Date: LocalDate
4. Authors
5. Tags
6. Status
7. Type
8. Collection
9. isFavorite

# Entertainment Segment: Episode

1. Entertainment ID
2. Title
3. Release Date: LocalDate
4. Segment Num
5. Tags
6. Status
7. Type
8. Collection
9. isFavorite
10. isPilot
11. isSpecial
# Entertainment Segment: Issue

1. Entertainment ID
2. Title
3. Release Date: LocalDate
4. Segment Num
5. Tags
6. Status
7. Type
8. Collection
9. isFavorite
# Entertainment Segment: Chapter

1. Entertainment ID
2. Title
3. Release Date: LocalDate
4. Chapter Num
5. Tags
6. Status
7. Type
8. Collection
9. isFavorite

# Manga / Manhwa

1. Entertainment ID
2. Title
3. Release Date
4. Chapter Count
5. Chapter List
6. Collection
7. Tags
8. isFavorite

| Fields               | Entertainment | Movie | Show/Anime  | Episode | Book          | Chapter | Comic | Issue | Manga / Manhwa |
| -------------------- | ------------- | ----- | ----------- | ------- | ------------- | ------- | ----- | ----- | -------------- |
| Entertainment ID     | y             | y     | y           | y       | y             | y       | y     | y     | y              |
| Title                | y             | y     | y           | y       | y             | y       | y     | y     | y              |
| isFavorite           | y             | y     | y           | y       | y             | y       | y     | y     | y              |
| Collections          | y             | y     | y           | y       | y             | y       | y     | y     | y              |
| Tags                 | y             | y     | y           | y       | y             | y       | y     | y     | y              |
| Status               | y             | y     | y           | y       | y             | y       | y     | y     | y              |
| Type                 | y             | y     | y           | y       | y             | y       | y     | y     | y              |
| Release Date         | y             | y     | y           | y       | y             | y       | y     | y     | y              |
|                      |               |       |             |         |               |         |       |       |                |
| isSpecial            |               | y     | y           | y       |               |         |       |       |                |
| isPilot              |               | y     | y           | y       |               |         |       |       |                |
| Runtime              |               | y     |             | y       |               |         |       |       |                |
| Production Companies |               | y     | y           | y       | y (publisher) |         | y     |       | y              |
|                      |               |       |             |         |               |         |       |       |                |
| Authors              |               |       |             |         | y             | y       | y     | y     | y              |
|                      |               |       |             |         |               |         |       |       |                |
| Segment Number       |               |       |             | y       |               | y       |       | y     |                |
|                      |               |       |             |         |               |         |       |       |                |
| Segment Count        |               |       | y (Episode) |         | y (Chapter)   |         |       |       |                |
| Segment List         |               |       | y (Episode) |         | y (Chapter)   |         |       |       |                |
|                      |               |       |             |         |               |         |       |       |                |
|                      |               |       |             |         |               |         |       |       |                |

