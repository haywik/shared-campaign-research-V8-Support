> [!NOTE]
> This mod has been forked to support Mindustry Build v160.5
> This fork could be unstable and unreliable.

# Shared Campaign Research

A co-op campaign mod for shared research. Players who joined the host can request research from the research screen, and the result is applied to the current campaign.

## Features

- adds a research request button for joined players in campaign;
- lets players request locked research from the research screen;
- applies research through the host, so progress stays tied to the campaign;
- updates research progress and available branch resources for clients;
- does not bypass the usual requirements or missing resources.

## Limits

- works only in campaign;
- research logic stays on the host;

## Build

Requirements:

- JDK 17;

Build command:

```bash
./gradlew mergeJar
```

Build outputs are placed in `build/libs/`.

Typical artifacts:

- `build/libs/shared-campaign-research-<version>.jar`
- `build/libs/shared-campaign-research-<version>-dexed.jar`

## License

MIT.
