# Regenerates assets/gnomefolk/textures/entity/gnome.png (64x64). Needs Pillow.
import random
from PIL import Image
random.seed(7)
img = Image.new("RGBA", (64, 64), (0, 0, 0, 0))
px = img.load()

RED=(214,40,40,255); RED_D=(170,26,26,255); RED_L=(250,48,48,255)
BLUE=(42,108,184,255); BLUE_D=(32,84,148,255); BLUE_L=(56,128,205,255)
WHITE=(255,255,255,255); CREAM=(255,250,185,255); CREAM_D=(225,215,150,255)
BOOT=(66,42,28,255); BOOT_D=(36,22,14,255)

def box(u, v, w, h, d, base, dark=None, light=None, speckles=0):
    W, H = 2*d + 2*w, d + h
    for x in range(u, u+W):
        for y in range(v, v+H):
            px[x, y] = base
    for _ in range(speckles):           # 2x2 pixel blotches like the reference
        c = random.choice([c for c in (dark, light) if c])
        sx, sy = random.randrange(u, u+W-1), random.randrange(v, v+H-1)
        for x in (sx, sx+1):
            for y in (sy, sy+1):
                px[x, y] = c

# (u,v,w,h,d) must match GnomeModel texOffs / box sizes
box(0, 0, 6, 4, 6, BLUE, BLUE_D, BLUE_L, 6)
# red drips on the tunic front, just under the hat band
fx, fy = 0 + 6, 0 + 6        # front face origin = (u+d, v+d)
for dy in range(0, 2): px[fx+0, fy+dy] = RED
for dy in range(0, 3): px[fx+5, fy+dy] = RED
box(0, 10, 6, 3, 6, RED, RED_D, RED_L, 5)
box(0, 19, 6, 4, 6, RED, RED_D, RED_L, 6)
box(0, 29, 4, 3, 4, RED, RED_D, RED_L, 3)
box(0, 36, 2, 3, 2, RED, RED_D, RED_L, 1)
box(0, 41, 6, 2, 1, WHITE)
box(0, 44, 4, 2, 1, WHITE)
box(0, 47, 2, 1, 1, WHITE)
box(0, 49, 2, 2, 1, CREAM, CREAM_D)
box(30, 0, 2, 3, 2, BLUE, BLUE_D, BLUE_L, 1)
box(30, 5, 2, 1, 2, CREAM, CREAM_D)
box(30, 8, 4, 1, 4, BOOT, BOOT_D, None, 3)

img.save("src/main/resources/assets/gnomefolk/textures/entity/gnome.png")
img.resize((512, 512), Image.NEAREST).save("/tmp/gnome_tex_preview.png")
