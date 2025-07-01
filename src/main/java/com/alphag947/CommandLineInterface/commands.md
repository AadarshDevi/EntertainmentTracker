# CommandLine Interface

## Keys

- [Enter] > enter key

## Master Keys

- mkc > master key change
- mkd > master key delete

## CommandLine Interface

- cmd list > list commands
- cmd exit > exit app

## Entertainment Statuses

### Statuses

- 1: Completed
- 2: Released
- 3: Upcoming
- 4: Special
- 5: Pilot
- 6: Favorite
- 7: Reviewd
- 9: Ongoing

### Commands

- entertainment status get primary <1,2,3,9>
- entertainment s g p <1,2,3,9>

- entertainment status get secondary <4,5,6,7>
- entertainment s g s <4,5,6,7>

- entertainment status set primary <1,2,3,9>
- entertainment s s p <1,2,3,9>

- entertainment status set secondary <4,5,6,7>
- entertainment s s s <4,5,6,7>

- entertainment status list <1,2,3,4,5,6,7,9>
- entertainment s l <1,2,3,4,5,6,7,9>

- if show |--- entertainment is show. get episode? <episode_id/[Enter]>

## Entertainment Id

- entertainment id get <id>
- entertainment id set <id> mk
- |--- if there is no "mk" at end, "access denied."
