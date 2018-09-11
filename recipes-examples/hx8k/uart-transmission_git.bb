# Template from:
# https://github.com/nathanrossi/gps-clock-project\
# /blob/master/meta-gps-clock/recipes\
# /display-controller/display-controller.bb

SRC_URI = "git://github.com/smoe/ice40_examples"
SRCREV = "e613665af53c9b79b3afa98281963d763e845b75"
S = "${WORKDIR}/git"

LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84dcc94da3adb52b53ae4fa38fe49e5d"

INHIBIT_DEFAULT_DEPS = "1"
DEPENDS = "yosys-native arachne-pnr-native icestorm-native icarus-verilog-native"

export IVERILOG = "${STAGING_DIR_NATIVE}${bindir_native}/iverilog -B${STAGING_DIR_NATIVE}${libdir_native}/ivl"
export VVP = "${STAGING_DIR_NATIVE}${bindir_native}/vvp -M${STAGING_DIR_NATIVE}${libdir_native}/ivl"
export ICEBOX = "${STAGING_DIR_NATIVE}${datadir_native}/icebox"

do_compile () {
    oe_runmake -C ${S}/uart_transmission
}

do_install () {
    install -d ${D}${libdir}/firmware
    install -m 0644 ${S}/uart_transmission/build/uart.bin \
        ${D}${libdir}/firmware/uart-transmission.bin
}

FILES_${PN} = "${libdir}/firmware/uart-transmission.bin"
