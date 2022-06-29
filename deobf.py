import os, re
import pandas as pd

SRC_DIR = "out"
OUT_DIR = "main/java/"

fields_map = pd.read_csv("fields.csv")
methods_map = pd.read_csv("methods.csv")

fields_pattern = re.compile(r'field_\d+_[A-Za-z]+_*')
methods_pattern = re.compile(r'func_\d+_[A-Za-z]+_*')

if not os.path.exists(OUT_DIR):
    os.mkdir(OUT_DIR)

for root, dirs, names in os.walk(SRC_DIR):
    for dir in dirs:
        dirname = os.path.join(root, dir)
        output_dirname = OUT_DIR + dirname[3:]
        if not os.path.exists(output_dirname):
            os.mkdir(output_dirname)
    for name in names:
        filename = os.path.join(root, name)
        lines = open(filename, 'r').readlines()
        output_lines = []
        for line in lines:
            fields_res = fields_pattern.findall(line)
            for srg in fields_res:
                line = line.replace(
                    srg,
                    fields_map.query('searge=="{}"'.format(srg)).iat[0, 1])
            methods_res = methods_pattern.findall(line)
            for srg in methods_res:
                line = line.replace(
                    srg,
                    methods_map.query('searge=="{}"'.format(srg)).iat[0, 1])
            output_lines.append(line)
        output_name = OUT_DIR + filename[3:]
        with open(output_name, 'w') as output:
            output.writelines(output_lines)
        print("Finishing ", filename)
