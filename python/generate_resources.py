import json
import shutil
from pathlib import Path


def generate_json_files():
    """
    This script performs the generation of the resources:

    It looks for the to_generate folder in the current directory
    It identifies each folder inside to_generate as a mod ID
    For each mod ID folder, it:

    Creates the required destination directories
    Copies the PNG textures to Common/src/main/resources/assets/mod_id/textures/item/enchanted_book/
    Generates JSON files with the proper structure at Common/src/main/resources/assets/mod_id/models/item/enchanted_book/
    """

    # Base directory containing mod folders
    base_dir = Path("to_generate")

    # Check if base directory exists
    if not base_dir.exists():
        print(f"Error: '{base_dir}' directory not found.")
        return

    # Process each mod directory
    for mod_dir in [d for d in base_dir.iterdir() if d.is_dir()]:
        mod_id = mod_dir.name
        print(f"Processing mod: {mod_id}")

        # Create destination directories if they don't exist
        texture_dest_dir = Path(f"../Common/src/main/resources/assets/{mod_id}/textures/item/enchanted_book")
        json_dest_dir = Path(f"../Common/src/main/resources/assets/{mod_id}/models/item/enchanted_book")

        texture_dest_dir.mkdir(parents=True, exist_ok=True)
        json_dest_dir.mkdir(parents=True, exist_ok=True)

        # Find all PNG files in the mod directory
        png_files = list(mod_dir.glob("*.png"))

        if not png_files:
            print(f"No PNG files found in {mod_id} directory.")
            continue

        # Process each PNG file
        for png_file in png_files:
            texture_name = png_file.stem  # Get filename without extension

            # Copy texture to destination
            texture_dest = texture_dest_dir / png_file.name
            print(f"Moving texture: {png_file.name} to {texture_dest}")
            shutil.copy2(png_file, texture_dest)

            # Create JSON file
            json_content = {
                "parent": "minecraft:item/generated",
                "textures": {
                    "layer0": f"{mod_id}:item/enchanted_book/{texture_name}"
                }
            }

            # Write JSON file
            json_path = json_dest_dir / f"{texture_name}.json"
            print(f"Creating JSON file: {json_path}")

            with open(json_path, 'w') as json_file:
                json.dump(json_content, json_file, indent=2)

        print(f"Completed processing for mod: {mod_id}")
        print()


if __name__ == "__main__":
    print("Starting texture and JSON file generation process...")
    generate_json_files()
    print("Process completed!")