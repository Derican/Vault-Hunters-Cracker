import os, random
from nbt import nbt
from nbt.nbt import *

TARGET_DIR = 'main/resources/data/the_vault/structures'

random_blocks = [
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

for root, dirnames, filenames in os.walk(TARGET_DIR):
    for filename in filenames:
        fullname = os.path.join(root, filename)
        nbtfile = NBTFile(fullname)
        for pallete in nbtfile['palette']:
            if 'minecraft' not in pallete[
                    'Name'].value and 'the_vault' not in pallete['Name'].value:
                print(pallete['Name'].value)
                pallete['Name'].value = random.choice(random_blocks)
        deleted_entities = []
        for entity in nbtfile['entities']:
            if 'nbt' in entity:
                if 'id' in entity['nbt']:
                    if ':' in entity['nbt'][
                            'id'].value and 'minecraft' not in entity['nbt'][
                                'id'].value and 'the_vault' not in entity[
                                    'nbt']['id'].value:
                        print(entity['nbt']['id'].value)
                        deleted_entities.append(entity)
        for entity in deleted_entities:
            nbtfile['entities'].remove(entity)
        deleted_blocks = []
        for block in nbtfile['blocks']:
            flag = False
            if 'nbt' in block:
                if 'id' in block['nbt']:
                    if ':' in block['nbt'][
                            'id'].value and 'minecraft' not in block['nbt'][
                                'id'].value and 'the_vault' not in block[
                                    'nbt']['id'].value:
                        print(block['nbt']['id'].value)
                        flag = True
                if 'final_state' in block['nbt']:
                    if ':' in block['nbt'][
                            'final_state'].value and 'minecraft' not in block[
                                'nbt'][
                                    'final_state'].value and 'the_vault' not in block[
                                        'nbt']['final_state'].value:
                        print(block['nbt']['final_state'].value)
                        flag = True
            if flag:
                deleted_blocks.append(block)
        for block in deleted_blocks:
            nbtfile['blocks'].remove(block)
        nbtfile.write_file(fullname)
        print('Finishing ' + fullname)
