import sys
import pathlib

def onlyName(s):
    return s.split("/")[-1].split(".")[0]

def importPath(s):
    moduleList = s.split("/")
    ret = ""
    for i in range (1, len(moduleList)):
        ret += "."
        ret += moduleList[i].split(".")[0]
    return ret

def genEntityTheme(entity, theme):
    entityName = onlyName(entity)
    themeName = onlyName(theme)

    sys.stdout = open("../entities_themes/"+entityName+themeName+".java", "w+")

    print(f"package namedEntity.entities_themes;\n"
          f"\n"
          f"import namedEntity{importPath(entity)};\n"
          f"import namedEntity{importPath(theme)};\n"
          f"\n"
          f"public class {entityName}{themeName} extends {entityName} implements {themeName} {{\n"
          f"\n"
          f"    public {entityName}{themeName}(String name, String category, int frequency) {{\n"
          f"        super(name, category, frequency);\n"
          f"    }}\n"
          f"\n"
          f"    public {entityName}{themeName}() {{\n"
          f"        super();\n"
          f"        setCategory(\"{entityName}\");\n"
          f"        setTheme(\"{themeName}\");\n"
          f"    }}\n"
          f"\n"
          f"}}\n"
          )

    sys.stdout = sys.__stdout__


entities = [ file.as_posix() for file in pathlib.Path('../entities').glob("**/*") if file.suffix == ".java" ]

themes = [ file.as_posix() for file in pathlib.Path('../themes').glob("**/*") if file.suffix == ".java" ]

for i in range(1, len(entities)):
    for j in range(1, len(themes)):
        genEntityTheme(entities[i], themes[j])