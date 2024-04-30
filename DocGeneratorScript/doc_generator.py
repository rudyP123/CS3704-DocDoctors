# How to use
# python doc_generator.py python path/to/your_file.py
# python doc_generator.py java path/to/your_file.java

import ast
import sys
import os
import configparser
from openai import OpenAI

def load_api_key(config_file='config.ini'):
    config = configparser.ConfigParser()
    config.read(config_file)
    api_key = config['openai']['api_key']
    return api_key

client = OpenAI(api_key=load_api_key())

def generate_documentation(prompt):
    try:
        response = client.chat.completions.create(model="gpt-3.5-turbo", 
        messages=[
            {"role": "system", "content": "You are a helpful assistant."},
            {"role": "user", "content": prompt}
        ])
        generated_doc = response.choices[0].message.content.strip()
        print("Generated Documentation:", generated_doc)
        return generated_doc
    except Exception as e:
        print(f"An error occurred: {e}")
        return "Documentation generation failed."
    
def add_docstrings_python(filepath):
    output_filepath = f"{os.path.splitext(filepath)[0]}_documented{os.path.splitext(filepath)[1]}"
    with open(filepath, "r") as file:
        lines = file.readlines()

    def get_function_start_line_numbers(lines):
        function_start_lines = []
        for i, line in enumerate(lines):
            if line.strip().startswith("def "):
                function_start_lines.append(i)
        return function_start_lines

    function_start_lines = get_function_start_line_numbers(lines)
    for start_line in function_start_lines:
        function_lines = []
        for line in lines[start_line:]:
            function_lines.append(line)
            if line.strip().startswith("def ") or line.strip().startswith("class ") or "return" in line:
                break
        function_text = "".join(function_lines)
        function_name = function_text.split("(")[0].split("def ")[1].strip()
        description = f"Summarize the functionality of the Python function '{function_name}'."
        docstring = generate_documentation(description)
        docstring_formatted = f'"""{docstring}"""\n'
        lines.insert(start_line + 1, docstring_formatted)

    new_source = "".join(lines)
    with open(output_filepath, "w") as file:
        file.write(new_source)

    return output_filepath

def add_javadocs_java(filepath):
    """Reads a Java file, adds Javadoc comments to methods."""
    output_filepath = f"{os.path.splitext(filepath)[0]}_documented{os.path.splitext(filepath)[1]}"
    
    with open(filepath, "r") as file:
        lines = file.readlines()

    new_lines = []
    for i, line in enumerate(lines):
        if (line.strip().startswith(("public", "private", "protected")) and "(" in line and ")" in line and "{" in line
                and not any(keyword in line for keyword in ["class ", "=", ";"])):
            method_signature = line.strip()
            description = f"""Write a detailed but concise Javadoc comment for the following 
            Java method: {method_signature}"""
            print("Method Signature:", method_signature)
            javadoc = generate_documentation(description)
            print("Generated Javadoc:", javadoc)
            new_lines.append(javadoc + "\n")  
        new_lines.append(line)

    with open(output_filepath, "w") as file:
        file.writelines(new_lines)
    return output_filepath

def main():
    if len(sys.argv) < 3:
        print("Usage: script.py <language> <input_file_path>")
        sys.exit(1)

    language = sys.argv[1].lower()
    input_filepath = sys.argv[2]
    output_filepath = ""

    if language == "python":
        output_filepath = add_docstrings_python(input_filepath)
    elif language == "java":
        output_filepath = add_javadocs_java(input_filepath)
    else:
        print("Unsupported language. Only Python and Java are supported.")
    
    print(f"Documented file created: {output_filepath}")

if __name__ == "__main__":
    main()