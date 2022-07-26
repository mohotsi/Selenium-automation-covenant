# covenant_automation_solution

![Love looks aaround to see who is in need]([project-image-url](https://scontent.fcpt5-1.fna.fbcdn.net/v/t1.18169-9/1239727_4764395487726_1261430237_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=19026a&_nc_eui2=AeFPQuf1gAiZPc9dTmHYCIci6QDQuTp4OUTpANC5Ong5RAVjiraQfbilGlPv-VWPcRg&_nc_ohc=wBYee7BWjo4AX_8lcS5&_nc_oc=AQkBwEs9MzeYyVtBJ-e_L2UkJbt_4N2eYL87G9rPnKu8jcxKhFj9vENu8Gz4aZoMVSg&_nc_pt=5&_nc_ht=scontent.fcpt5-1.fna&oh=00_AT_-KDhP1U1TXWPSXL4U2twU34tgWYWdd8cSYgJkRjhDvQ&oe=63058D33))

> Test automation solution

---

### Table of Contents


- [Description](#description)
- [Virtual machine installation and setup](#How_to_get_our_virtual_box_up_and_running)
- [Installing docker images](#Images_installation)
- [Running automation pack](#running_the_automation_pack)
- [License](#license)
- [Author Info](#author-info)

---

## Description
automation assignment to simulate the behaviour of a user which interacts with the Covenant webapp
#### Technologies

- Java 11
- selenium
- Cucumber
- springboot maven frame work (POM)
- jsch-extension
- git
- docker

[Back To The Top](#covenant automation solution)

## How_to_get_our_virtual_box_up_and_running
### install on Unix(linux)
    $>sudo apt-get install virtualbox
- create new virtual machine with Windows 10
- install Windows 10 OS
- [set up port forwarding](https://www.howtogeek.com/122641/how-to-forward-ports-to-a-virtual-machine-and-use-it-as-a-server/)
- start up the machine
- install OPenSSH on Windows
- Disable Windows defender and windows anti virus
- sudo apt-get install virtualbox
- open powershell as admin and type the following:
  windows/system32> Start-Service sshd
  windows/system32> Enable-Remoting sshd
- if you can ssh into the virtual machine you are good to go

## How To Use

## Images_installation
### Pull docker-selenium

     docker pull elgalu/selenium
### Pull Zalenium
    docker pull dosel/zalenium
    
    # Run it!
    sudo docker run --rm -ti --name zalenium -p 4444:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/seluser/videos \
      --privileged dosel/zalenium start --desiredContainers 1
### covenant (Software under testing)
     $ ~ > git clone --recurse-submodules https://github.com/cobbr/Covenant
     $ ~ > cd Covenant/Covenant
     $ ~/Covenant/Covenant >sudo docker build -t covenant .

### Jenkins (ci pipeline)
    sudo docker run -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts-jdk11

#### running_the_automation_pack
- selenium grid /zalenium docker image must be running (!)
- virtual machine must be on (!)

       $~> rm -rf automationworkspace
       $~> mkdir automationworkspace
       $~> cd automationworkspace
       $~> git clone https://github.com/mohotsi/Selenium-automation-covenant.git
       $~> cd Selenium-automation-covenant

#### run automation remote
       $~> mvn clean test -Dbrowser=chrome -Dspring.profiles.active=remote
#### run it locally
       $-> mvn clean test
## License

Copyright (c) [2022] [Thapelo]
For helping me get vacancies

[Back To The Top](covenant_automation_solution)

---

## Author Info

- linkedin - [Thapelo Daniel Mohotsi](https://www.linkedin.com/in/thapelo-daniel-mohotsi-1b752a42/)
- github - [Thapelo Daniel Mohotsi](https://github.com/mohotsi)

[Back To The Top](#covenant_automation_solution)



