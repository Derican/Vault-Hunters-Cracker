import os, re, random

TARGET_DIR = 'main/resources/data/the_vault/loot_tables'

LOOT_POOL = [
    'minecraft:netherite_block', 'minecraft:nether_star',
    'minecraft:dragon_breath', 'minecraft:dragon_egg',
    'minecraft:emerald_block', 'minecraft:enchanted_golden_apple',
    'minecraft:glistering_melon_slice', 'minecraft:nautilus_shell',
    'minecraft:sponge'
]
id_pattern = re.compile(r'"name": "([A-Za-z0-9_]+:[A-Za-z0-9_]+)"')

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
                    if 'minecraft:' in block_id or 'the_vault:' in block_id or 'forge:' in block_id:
                        continue
                    print(block_id)
                    line = line.replace(block_id, random.choice(LOOT_POOL))
                output_lines.append(line)
        with open(filename, 'w') as output:
            output.writelines(output_lines)
        print('Finishing ' + filename)
