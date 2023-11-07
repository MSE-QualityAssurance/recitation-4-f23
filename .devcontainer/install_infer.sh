#!/bin/bash

# Download Infer
wget https://github.com/facebook/infer/releases/download/v1.1.0/infer-linux64-v1.1.0.tar.xz

# Unzip the downloaded file
sudo tar -xf infer-linux64-v1.1.0.tar.xz -C /opt

# Set up Infer alias (this will only be available in the shell that runs this script)
echo 'export PATH=/opt/infer-linux64-1.1.0/bin:$PATH' >> ~/.bashrc
source ~/.bashrc

# Remove the infer-linux
sudo rm infer-linux64-v1.1.0.tar.xz