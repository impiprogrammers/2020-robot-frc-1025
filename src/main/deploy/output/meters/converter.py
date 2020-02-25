import json
import os

pathNames = [
    'center3-1', 'center8-1', 'center8-1', 'center8-2', 'center8-3', 'left3-1', 'left8-1', 'left8-2', 'left8-3',
    'right_shield10-1', 'right_shield10-2', 'right_shield10-3', 'right_shield10-4', 'right_trench10-1', 'right_trench10-2',
    'right_trench10-3', 'right_trench10-4', 'right0-1', 'right3-1'
]

for pathName in pathNames:
    data = 0
    fileName = 'src/main/deploy/output/meters/' + pathName + '.wpilib.json'
    with open(fileName, 'r') as file:
        data = json.load(file)
        # data = data[0]
        for step in data:
            x = step['pose']['translation']['x']
            y = step['pose']['translation']['y']
            x *= 0.0254
            y *= 0.0254
            step['pose']['translation']['x'] = x
            step['pose']['translation']['y'] = y
    with open(fileName, 'w') as file:
        json.dump(data, file)