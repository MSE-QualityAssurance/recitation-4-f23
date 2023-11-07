#!/bin/bash

# Navigate to the home directory
cd $HOME

# Download PMD
wget https://github.com/pmd/pmd/releases/download/pmd_releases%2F7.0.0-rc4/pmd-dist-7.0.0-rc4-bin.zip

# Unzip the downloaded file
unzip pmd-dist-7.0.0-rc4-bin.zip

# Set up PMD alias (this will only be available in the shell that runs this script)
alias pmd="$HOME/pmd-bin-7.0.0-rc4/bin/run.sh pmd"
