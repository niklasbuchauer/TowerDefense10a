# TowerDefense10a

Dies ist ein Tower Defense Spiel in dem Style von Dem Spiel "Bloons Tower Defense"
Es wurde programmiert und designed von Niklas Buchauer, Maxi Soos, John Tiberius und Dan Dumitra.
Fast alles wurde in VS Code programmiert und in BlueJ übertragen.
      
Spiel Struktur:

```

├── README.md / README.TXT                 # Project documentation
├── package.bluej                          # BlueJ project configuration
│
├── Core System/                           # Main game loop, state, und player management
│   ├── Main.java
│   ├── Game.java
│   ├── GameState.java
│   └── Player.java
│
├── Maps & Environment/                    # Map generation, tiles, und map selection
│   ├── GameMap.java
│   ├── GameMapAlt.java
│   ├── MapData.java
│   ├── MapSelector.java
│   ├── MapType.java
│   ├── Tile.java
│   └── TileType.java
│
├── Game Engine & UI/                      # Wave manager und UX/UI
│   ├── WaveManager.java
│   ├── Hud.java
│   ├── TowerInfoPanel.java
│   └── FloatingText.java
│
├── Towers/                                # Tower implementation und logik
│   ├── Tower.java
│   ├── BasicTower.java
│   ├── FreezeTower.java
│   ├── RapidTower.java
│   └── SniperTower.java
│
├── Projectiles/                           # Projectiles 
│   ├── Bullet.java
│   ├── ExplosionBullet.java
│   ├── FreezeBullet.java
│   ├── PierceBullet.java
│   ├── PoisonBullet.java
│   ├── RailgunBullet.java
│   └── SniperBullet.java
│
├── Enemies/                               # Standart Enemy Klasse und Varianten
│   ├── Enemy.java
│   ├── ArmoredEnemy.java
│   ├── BossEnemy.java
│   ├── FastEnemy.java
│   ├── HealerEnemy.java
│   ├── IceEnemy.java
│   ├── NormalEnemy.java
│   ├── RegenEnemy.java
│   ├── SplitterEnemy.java
│   ├── StealthEnemy.java
│   └── TankEnemy.java
│
├── Bosses/                                # Bosse
│   ├── ArmorBoss.java
│   ├── DemonBoss.java
│   ├── EmperorBoss.java
│   ├── EndKingBoss.java
│   ├── FrostlordBoss.java
│   ├── KingBoss.java
│   ├── NecromancerBoss.java
│   ├── ShadowBoss.java
│   ├── TitanBoss.java
│   └── WatcherBoss.java
│
└── Visual Effects/                        # Gameplay und visuelle Effekte
    ├── IceSplinterEffect.java
    └── PoisonEffect.java
```

          
