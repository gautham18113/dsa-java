# ClipHUD — v1 Prototype

A tiny **universal HUD module that clips onto existing glasses** and turns them
into an AI display — without forcing users to change frames, lenses, or
prescriptions.

This folder is a **working prototype of the v1 experience**, not the hardware.
It's an interactive, self-contained simulator of the glance display so you can
*show* the idea today.

## Run it

Open `index.html` in any browser.

- **On a laptop:** double-click the file. Use the buttons at the bottom to switch
  use cases, or hit **Auto-demo** to cycle through all of them.
- **On a phone (the real demo):** open the file in mobile Safari/Chrome, then
  **hold the phone behind one lens of a pair of glasses**, screen facing your eye.
  The dark scene drops away and only the glowing panel "floats" in the corner of
  your vision — that's the monocular combiner experience ClipHUD recreates with
  real optics.

> The dark grid is a stand-in for the real world (a see-through combiner shows
> the actual world behind it). Only the glowing cyan panel is "projected" light.

## What v1 actually is (the spec)

These are deliberate scope choices that make v1 buildable rather than a moonshot:

| Decision | v1 choice | Why |
|---|---|---|
| **Display type** | **Glance display, not AR.** Small monochrome text/icon panel in the corner of one eye. | Kills the optics, compute, and battery cost of world-locked holograms. It's exactly what an AI assistant needs. |
| **Eyes** | **Monocular** (right by default, switchable). | One micro-display + one combiner = half the weight, cost, and alignment pain. |
| **Compute** | **Phone-tethered over BLE.** The clip is a display + sensors; the phone runs the AI. | Slashes weight, heat, and power on the head. Leverages the phone you already carry. |
| **Camera** | **None in v1.** | Sidesteps the social-acceptance / privacy backlash that killed Google Glass. Adds later as an opt-in module. |
| **Mount** | **Adjustable temple clip**, a small family of sizes, not one rigid "universal" part. | Truly one-size can't align an image to every pupil. "Fits most frames" with a slider is honest and shippable. |

### v1 use cases (all shown in the simulator)

1. **Notifications** — texts, calendar pings, glanceable and dismissible.
2. **Navigation** — turn-by-turn arrow + distance, eyes-up.
3. **Live caption / translation** — real-time speech → text in your eyeline.
4. **AI assistant** — ask, get a short answer projected as a glance card.

## Rough v1 bill of materials (hardware target)

- Micro-display: monochrome microLED or small LCoS (~0.13–0.2", high nits for daylight).
- Combiner: small birdbath / reflective prism on an adjustable arm (cheaper & brighter
  than a waveguide; fine for a corner glance panel).
- SoC: low-power BLE MCU (e.g. nRF-class) — display driver + IMU, *not* an AP.
- Sensors: 6-axis IMU (head gestures / nav stabilization), ambient light (auto-brightness).
- Power: small ~120–180 mAh cell in the temple clip; target all-day via phone offload.
- Link: BLE to a companion phone app that hosts the AI + connectivity.

## How this prototype maps to the real device

| Prototype piece | Real device equivalent |
|---|---|
| Cyan glowing panel | Light projected through the combiner into one eye |
| Dark grid background | The see-through real world behind the combiner |
| `Eye: R/L` toggle | Mounting the clip on the left or right temple |
| Status bar (BLE · 🔋) | The clip's link to the phone + onboard battery |
| Phone held behind a lens | The clip + combiner sitting on your existing frame |

## Next steps toward a physical prototype

1. **Bench rig:** drive a dev microLED/LCoS module from an nRF dev board over BLE,
   mirroring this UI. Confirms brightness + latency.
2. **Optical mule:** 3D-print an adjustable temple clip + prism holder; tune
   eye-relief and the image landing spot across 3–4 frame widths.
3. **Companion app:** thin phone app that pushes these four card types over BLE
   (the AI/assistant logic lives here).
