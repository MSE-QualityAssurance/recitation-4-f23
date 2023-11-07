#!/bin/bash

# Navigate to the home directory
cd $HOME

# Download PMD
wget https://github.com/pmd/pmd/releases/download/pmd_releases%2F7.0.0-rc4/pmd-dist-7.0.0-rc4-bin.zip

# Unzip the downloaded file
unzip pmd-dist-7.0.0-rc4-bin.zip

# Export PMD to the PATH
echo 'export PATH=/home/vscode/pmd-bin-7.0.0-rc4/bin/:$PATH' >> ~/.bashrc
