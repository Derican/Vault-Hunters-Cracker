import os, re, random

TARGET_DIR = 'main/resources/data/the_vault/worldgen'

GEN_POOL = [
    'minecraft:black_wool', 'minecraft:blue_wool', 'minecraft:brown_wool',
    'minecraft:cyan_wool', 'minecraft:gray_wool', 'minecraft:green_wool',
    'minecraft:light_blue_wool', 'minecraft:light_gray_wool',
    'minecraft:lime_wool', 'minecraft:magenta_wool', 'minecraft:orange_wool',
    'minecraft:pink_wool', 'minecraft:purple_wool', 'minecraft:red_wool',
    'minecraft:white_wool', 'minecraft:yellow_wool',
    'minecraft:black_stained_glass', 'minecraft:blue_stained_glass',
    'minecraft:brown_stained_glass', 'minecraft:cyan_stained_glass',
    'minecraft:gray_stained_glass', 'minecraft:green_stained_glass',
    'minecraft:light_blue_stained_glass', 'minecraft:light_gray_stained_glass',
    'minecraft:lime_stained_glass', 'minecraft:magenta_stained_glass',
    'minecraft:orange_stained_glass', 'minecraft:pink_stained_glass',
    'minecraft:purple_stained_glass', 'minecraft:red_stained_glass',
    'minecraft:white_stained_glass', 'minecraft:yellow_stained_glass',
    'minecraft:glowstone'
]
id_pattern = re.compile(r'"([A-Za-z0-9_]+:[A-Za-z0-9_]+)"')

for root, dirs, names in os.walk(TARGET_DIR):
    for name in names:
        filename = os.path.join(root, name)
        output_lines = []
        with open(filename, 'r') as input:
            lines = input.readlines()
            for line in lines:
                res = id_pattern.findall(line)
                # print(res)
                for block_id in res:
                    if 'minecraft:' in block_id or 'the_vault:' in block_id:
                        continue
                    print(block_id)
                    line = line.replace(block_id, random.choice(GEN_POOL))
                output_lines.append(line)
        with open(filename, 'w') as output:
            output.writelines(output_lines)
        print('Finishing ' + filename)
