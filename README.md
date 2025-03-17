# Poketlin

A Pokémon Team Analyser to support your journey in becoming a Pokémon Master.

> [!NOTE]
> Feel free to download and use as you wish. :)

## Why?

This project is my learning experiment to improve my Kotlin skills.

## API

Below are the available endpoints in this project:

> [!WARNING]
> The majority of the API is always expecting the "happy path". So be aware that unhandled errors may occur.

#### /api/users

Creates a new User in the database.

```json
{
    "username": "my-name",
    "email": "my-email@email.com",
    "password": "my-pass-123"
}
```

#### /api/teams

Creates a new Team in the database.

```json
{
    "name": "my-team-name",
    "description": "my-team-description",
    "username": "my-team-owner-username"
}
```

### /api/teams/{teamId}

Returns an overview of a given Team.

> [!IMPORTANT]
> The list of Pokémons is transformed to return only the names at the moment.

```json
{
    "id": 1,
    "name": "my-team-name",
    "description": "my-team-description",
    "pokemonCount": 4,
    "createdAt": "2025-03-16T20:00:47.352056",
    "updatedAt": "2025-03-16T20:00:47.352076",
    "pokemons": [
        "Grimer",
        "Butterfree",
        "Mr-mime",
        "Gloom"
    ]
}
```

#### /api/teams/pokemon/add

Adds a new Pokémon to an already existing Team.

> [!IMPORTANT]
> The `pokemonId` field refers to the Pokémon ID in the Pokédex.

```json
{
    "pokemonId": 1,
    "teamId": 1
}
```

#### /api/teams/{teamId}/analysis/weaknesses

Runs an analysis on the weaknesses of a given Team.

#### /api/teams/{teamId}/analysis/stats

Runs an analysis to check which attributes are the strongest in the Team.

#### /api/teams/{teamId}/analysis/diversity

Runs an analysis and returns the diversity of the Team (the amount of Pokémons per Type).

