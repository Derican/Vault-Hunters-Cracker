# Vault Hunters Cracker

This project is to decompile [iskallia's Vault Hunters](https://github.com/Iskallia/Vault-public-S1) mod for my [Easy Vault](https://github.com/Derican/Easy-Vault) mod.

## Instructions

### Decompile Mod Jar

`java -jar procyon-decompiler-0.6.0.jar -jar the_vault-{vault_version}.jar -o out`

### Deobfuscation Source Code

`python deobf.py`

### Replace mod blocks with vanilla blocks

```python
python loot_table_del.py
python worldgen_del.py
python nbt_del.py
```
